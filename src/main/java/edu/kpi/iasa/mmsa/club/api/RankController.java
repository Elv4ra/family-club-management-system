package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.Rank;
import edu.kpi.iasa.mmsa.club.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping(value = "/ranks")
    public ResponseEntity<List<Rank>> fetchAll() {
        return ResponseEntity.ok(rankService.getAllRanks());
    }
}
