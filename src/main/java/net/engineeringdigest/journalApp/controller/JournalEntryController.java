package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(name =  "Journal APIs")
public class JournalEntryController {

      @Autowired
      private JournalEntryService journalEntryService;
      
      @Autowired
      private UserService userService;
    

      @GetMapping
      @Operation(summary = "gey All journal entries of User")
      public ResponseEntity<?> getAllJournalEntriesofUser(){
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String userName = authentication.getName();
          User user = userService.findByUserName(userName);
          List<JournalEntry> allEntries = user.getJournalEntries();
          if(allEntries !=null && !allEntries.isEmpty()){
              return new ResponseEntity<>(allEntries,HttpStatus.OK);
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      @PostMapping
      public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
          try{
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              String userName = authentication.getName();
              journalEntryService.saveEntry(journalEntry,userName);
              return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
          }catch(Exception e){
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
          }

      }

      @GetMapping("id/{Id}")
      public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String Id){
          ObjectId objectId = new ObjectId(Id);
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String userName = authentication.getName();
          User userObj = userService.findByUserName(userName);
          List<JournalEntry> collectEntriesById = userObj.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
          if(!collectEntriesById.isEmpty()){
              Optional<JournalEntry> journalEntry = journalEntryService.findById(objectId);
              //return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
              if(journalEntry.isPresent()){
                  return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
             }
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      @DeleteMapping("id/{Id}")
      public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId Id){
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String userName = authentication.getName();
          boolean deleted = journalEntryService.deleteById(Id, userName);
          if(deleted){
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }else{
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
      }

     @PutMapping("/id/{myId}")
      public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String userName = authentication.getName();
         User userObj = userService.findByUserName(userName);
         List<JournalEntry> collectEntriesById = userObj.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
         if(!collectEntriesById.isEmpty()){
             Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
             if(journalEntry.isPresent()){
                 JournalEntry oldEntry = journalEntry.get();
                 oldEntry.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
                 oldEntry.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
                 journalEntryService.saveEntry(oldEntry);
                 return new ResponseEntity<>(oldEntry,HttpStatus.OK);
             }
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }



}
