package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.repository.model.Finances;
import edu.kpi.iasa.mmsa.club.service.FinancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/finances")
public class FinancesController {

    private final FinancesService financesService;

    @Autowired
    public FinancesController(FinancesService financesService) {
        this.financesService = financesService;
    }

    @PostMapping
    public ResponseEntity<String> createFinance(@Valid @RequestBody Finances finance) {
        return ResponseEntity.ok(financesService.createFinance(finance));
    }

    @GetMapping
    public ResponseEntity<List<Finances>> readAll() {
        return ResponseEntity.ok(financesService.getAllFinances());
    }

    @GetMapping(value = "/event/{id}")
    public ResponseEntity<List<Finances>> readAllByEvent(@PathVariable long id) {
        return ResponseEntity.ok(financesService.getAllFinancesByEvent(id));
    }

    @GetMapping(value = "/alreadyPaid/{bool}")
    public ResponseEntity<List<Finances>> readAllByPaid(@PathVariable Boolean bool) {
        return ResponseEntity.ok(financesService.getAllFinancesByPaid(bool));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateRecord(@PathVariable long id) {
        return ResponseEntity.ok(financesService.updateFinance(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable long id) {
        return ResponseEntity.ok(financesService.deleteRecord(id));
    }
}
