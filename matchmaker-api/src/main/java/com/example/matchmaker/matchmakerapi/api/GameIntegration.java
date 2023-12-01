package com.example.matchmaker.matchmakerapi.api;

import com.example.matchmaker.matchmakerapi.service.dto.request.GameApiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "teste", url = "https://api.rawg.io/api/games")
public interface GameIntegration {
    @GetMapping
    GameApiRequest getGames(@RequestParam String key);
}
