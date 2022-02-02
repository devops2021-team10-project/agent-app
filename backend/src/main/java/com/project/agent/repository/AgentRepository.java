package com.project.agent.repository;

import com.project.agent.domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository  extends JpaRepository<Agent, Long> {
    Optional<Agent> findByUsername(String username);
}
