package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.Rank;
import edu.kpi.iasa.mmsa.club.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @PostMapping(value = "/ranks")
    public ResponseEntity<Rank> createRank(@Valid @RequestBody Rank newRank) {
        return ResponseEntity.ok(rankService.saveRank(newRank));
    }

    @GetMapping(value = "/ranks")
    public ResponseEntity<List<Rank>> findAll() {
        return ResponseEntity.ok(rankService.getAllRanks());
    }

    @GetMapping(value = "/ranks/{id}")
    public ResponseEntity<Rank> findById(@PathVariable long id) {
        return ResponseEntity.ok(rankService.getRankById(id));
    }

    @PutMapping(value = "/ranks/{id}")
    public ResponseEntity<Rank> updateRank(@PathVariable long id, @Valid @RequestBody Rank updatedRank) {
        return ResponseEntity.ok(rankService.updateRank(id, updatedRank));
    }

    @DeleteMapping(value = "/ranks/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        return ResponseEntity.ok(rankService.deleteRankById(id));
    }
}
