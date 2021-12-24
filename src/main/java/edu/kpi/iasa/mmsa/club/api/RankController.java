package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.Rank;
import edu.kpi.iasa.mmsa.club.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/ranks")
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @PostMapping
    public ResponseEntity<String> createRank(@RequestBody Rank newRank) {
        return ResponseEntity.ok(rankService.createRank(newRank));
    }

    @GetMapping
    public ResponseEntity<List<Rank>> readAll() {
        return ResponseEntity.ok(rankService.getAllRanks());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Rank> readById(@PathVariable long id) {
        return ResponseEntity.ok(rankService.getRankById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateRank(@PathVariable long id, @RequestBody Rank updatedRank) {
        return ResponseEntity.ok(rankService.updateRank(id, updatedRank));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        return ResponseEntity.ok(rankService.deleteRankById(id));
    }
}
