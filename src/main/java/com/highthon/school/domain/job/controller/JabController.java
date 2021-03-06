package com.highthon.school.domain.job.controller;

import com.highthon.school.domain.job.dto.CreateJabRequestDto;
import com.highthon.school.domain.job.dto.JabInfoResponseDto;
import com.highthon.school.domain.job.service.JabService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class JabController {
    private final JabService jabService;

    @PostMapping("/jab")
    public void createJab(@RequestBody CreateJabRequestDto req){
        jabService.createJab(req);
    }

    @GetMapping("/jab-list/{branch}")
    public List<JabInfoResponseDto> jabList(@PathVariable int branch){
        return jabService.jabList(branch);
    }

    // main
    @GetMapping("/jabs/{branch}")
    public List<JabInfoResponseDto> jabListToMain(@PathVariable int branch){
        return jabService.jabListToMain(branch);
    }

    @GetMapping("/jab/{name}")
    public JabInfoResponseDto jabSearch(@PathVariable String name) throws UnsupportedEncodingException {
        name = URLDecoder.decode(name, StandardCharsets.UTF_8);
        return jabService.getJabDetails(name);
    }

    @GetMapping("/jab/{branch}/{num}")
    public List<JabInfoResponseDto> jabLineUp(@PathVariable int branch, @PathVariable int num){
        if (num==0){
            return jabService.mostInterestJabList(branch);
        } else if(num == 1){
            return jabService.orderByJabNameList(branch);
        }
        return jabService.jabList(branch); // 최신순
    }
}
