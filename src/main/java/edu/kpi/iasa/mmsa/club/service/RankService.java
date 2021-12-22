package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.RankAlreadyExistsException;
import edu.kpi.iasa.mmsa.club.exception.RankNotFoundException;
import edu.kpi.iasa.mmsa.club.repository.RankRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Rank;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankService {

    private final RankRepository rankRepository;

    public RankService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    public List<Rank> getAllRanks() {
        return rankRepository.findAll();
    }

    public Rank getRankById(long id) {
        Optional<Rank> rank = rankRepository.findById(id);
        if (rank.isPresent()) {
            return rank.get();
        }
        throw new RankNotFoundException();
    }

    public Rank saveRank(Rank newRank) {
        if (!(rankRepository.findByRankName(newRank.getRankName()).getClass().getName().equals(newRank.getRankName()))) {
            return rankRepository.save(newRank);
        }
        throw new RankAlreadyExistsException();
    }

    public Rank updateRank(long id, Rank updatedRank) {
        Optional<Rank> oldRank = rankRepository.findById(id);
        if (oldRank.isPresent()) {
            oldRank.get().setRankName(updatedRank.getRankName());
            return  rankRepository.save(oldRank.get());
        }
        throw  new RankNotFoundException();
    }

    public String deleteRankById(long id) {
        if (rankRepository.findById(id).isPresent()) {
            rankRepository.deleteById(id);
            return "Rank was successfully deleted";
        }
        throw new RankNotFoundException();
    }
}
