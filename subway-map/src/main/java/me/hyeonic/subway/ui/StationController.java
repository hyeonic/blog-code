package me.hyeonic.subway.ui;

import java.net.URI;
import java.util.List;
import me.hyeonic.subway.dto.StationRequest;
import me.hyeonic.subway.dto.StationResponse;
import me.hyeonic.subway.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public ResponseEntity<StationResponse> createStation(@RequestBody StationRequest stationRequest) {
        StationResponse stationResponse = stationService.save(stationRequest);
        return ResponseEntity.created(URI.create("/stations/" + stationResponse.getId())).body(stationResponse);
    }

    @GetMapping
    public ResponseEntity<List<StationResponse>> showStations() {
        List<StationResponse> stationResponses = stationService.findAll();
        return ResponseEntity.ok(stationResponses);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
