package com.zomiles.JournalApp.controller;

import com.zomiles.JournalApp.entity.JournalEntry;
import com.zomiles.JournalApp.entity.User;
import com.zomiles.JournalApp.service.JournalEntryService;
import com.zomiles.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> entries = user.getJournalEntries();
        if(entries!=null && !entries.isEmpty()){
            return new ResponseEntity<>(entries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myFirstEntry,@PathVariable String userName) {
        try {
           // myFirstEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myFirstEntry,userName);
            return new ResponseEntity<>(myFirstEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteByID(@PathVariable ObjectId myId,@PathVariable String userName) {
         journalEntryService.deleteById(myId,userName);
         return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateByID(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry myEntry,
            @PathVariable String userName
    ) {
        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("")? myEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("")? myEntry.getContent():oldEntry.getContent());

            JournalEntry updatedEntry = journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(updatedEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
