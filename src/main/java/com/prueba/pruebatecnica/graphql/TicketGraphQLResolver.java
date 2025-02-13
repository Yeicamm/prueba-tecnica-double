package com.prueba.pruebatecnica.graphql;

import com.prueba.pruebatecnica.entity.TicketEntity;
import com.prueba.pruebatecnica.exception.TicketNotFoundException;
import com.prueba.pruebatecnica.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TicketGraphQLResolver {

    private final TicketRepository ticketRepository;

    @QueryMapping
    public TicketEntity getTicketById(@Argument Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @QueryMapping
    public List<TicketEntity> getAllTickets() {
        return ticketRepository.findAll();
    }

    @QueryMapping
    public List<TicketEntity> getTicketsByStatus(@Argument String estatus) {
        return ticketRepository.findByEstatus(TicketEntity.EstadoTicket.valueOf(estatus.toUpperCase()));
    }

    @MutationMapping
    public TicketEntity createTicket(@Argument String usuario, @Argument String estatus) {
        TicketEntity ticket = new TicketEntity();
        ticket.setUsuario(usuario);
        ticket.setEstatus(TicketEntity.EstadoTicket.valueOf(estatus.toUpperCase()));
        return ticketRepository.save(ticket);
    }

    @MutationMapping
    public TicketEntity updateTicket(@Argument Long id, @Argument String usuario, @Argument String estatus) {
        return ticketRepository.findById(id).map(ticket -> {
            if (usuario != null) ticket.setUsuario(usuario);
            if (estatus != null) ticket.setEstatus(TicketEntity.EstadoTicket.valueOf(estatus.toUpperCase()));
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new TicketNotFoundException(id));
    }

    @MutationMapping
    public String deleteTicket(@Argument Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }
        ticketRepository.deleteById(id);
        return "Ticket eliminado";
    }
}


