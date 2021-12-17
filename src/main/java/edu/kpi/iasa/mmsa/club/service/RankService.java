package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.repository.RankRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Rank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RankService {

    private final RankRepository rankRepository;

    public List<Rank> getAllRanks() {
        return rankRepository.findAll();
    }
}
