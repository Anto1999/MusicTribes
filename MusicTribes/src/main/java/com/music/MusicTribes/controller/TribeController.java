package com.music.MusicTribes.controller;

import com.music.MusicTribes.entity.Playlist;
import com.music.MusicTribes.entity.Song;
import com.music.MusicTribes.entity.Tribe;
import com.music.MusicTribes.repository.SongRepository;
import com.music.MusicTribes.repository.TribeRepository;
import com.music.MusicTribes.request.PlaylistRequest;
import com.music.MusicTribes.request.SongRequest;
import com.music.MusicTribes.request.TribeRequest;
import com.music.MusicTribes.service.SongService;
import com.music.MusicTribes.service.TribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/tribe")
public class TribeController {
    private final TribeService tribeService;

    @Autowired
    TribeRepository tribeRepository;

    @Autowired
    public TribeController(TribeService tribeService){
        this.tribeService=tribeService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity<Tribe> createTribe(@RequestBody TribeRequest tribeRequest){
        tribeService.createTribe(tribeRequest);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/join")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public String joinToTribe(@RequestParam("tribe_id") Long tribeId){

        tribeService.joinToTribe(tribeId);
        return "redirect:all";
    }
    @GetMapping("/svi")
    public List<Tribe> getAllTribes(){
        return tribeService.getTribes();
    }


    @GetMapping("/all")
    public String tribesView(Model model){
        model.addAttribute("tribe",tribeService.getTribes());
        return "index";
    }
    @GetMapping("{tribeId}")
    public String viewTribe( Model model,@PathVariable Long tribeId){
        model.addAttribute("tribe",tribeService.getPlaylist(tribeId));
        return "tribe";

    }

}
