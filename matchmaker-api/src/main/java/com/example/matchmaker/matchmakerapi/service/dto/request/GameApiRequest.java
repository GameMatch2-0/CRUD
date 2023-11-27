package com.example.matchmaker.matchmakerapi.service.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class GameApiRequest {
    private List<ResultApiDto> results;
}
