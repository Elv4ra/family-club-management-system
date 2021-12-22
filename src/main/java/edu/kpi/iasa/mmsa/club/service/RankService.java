package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.exception.InvalidInputDataException;
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

    public Rank getRankById(long id) {
        Optional<Rank> rank = rankRepository.findById(id);
        if (rank.isPresent()) {
            return rank.get();
        }
        throw new RankNotFoundException();
    }

    public List<Rank> getAllRanks() {
        List<Rank> ranks = rankRepository.findAll();
        if (ranks.isEmpty()) {
            throw new RankNotFoundException();
        }
        return ranks;
    }

    public String saveRank(Rank newRank) {
        if (!(rankRepository.findByRankName(newRank.getRankName()).getClass().getName().equals(newRank.getRankName()))) {
            try {
                rankRepository.save(newRank);
                return "New Rank was successfully created";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw new RankAlreadyExistsException();
    }

    public String updateRank(long id, Rank updatedRank) {
        Optional<Rank> oldRank = rankRepository.findById(id);
        if (oldRank.isPresent()) {
            try {
                oldRank.get().setRankName(updatedRank.getRankName());
                rankRepository.save(oldRank.get());
                return "Rank with id="+String.valueOf(id)+"was successfully updated";
            } catch (IllegalArgumentException e) {
                throw new InvalidInputDataException();
            }
        }
        throw  new RankNotFoundException();
    }

    public String deleteRankById(long id) {
        if (rankRepository.findById(id).isPresent()) {
            rankRepository.deleteById(id);
            return "Rank with id="+String.valueOf(id)+"was successfully deleted";
        }
        throw new RankNotFoundException();
    }
}
