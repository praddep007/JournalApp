package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

    //@Scheduled(cron = "0 0 9 * * SUN")
    //@Scheduled(cron = "0 * * ? * *")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            String subject = "Sentiment for last 7 Days";
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(a -> a.getSentiment()).collect(Collectors.toList());

            Map<Sentiment,Integer> sentimentsCounts = new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if(sentiment != null ){
                    sentimentsCounts.put(sentiment, sentimentsCounts.getOrDefault(sentiment,0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment,Integer> entry : sentimentsCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment != null){
                emailService.sendEmail(user.getEmail(),subject,mostFrequentSentiment.toString());
            }
        }
    }


//    //app is chache in every 10 mins
//    @Scheduled(cron = "0 0/10 * ? * *")
//    public void appCache(){
//        appCache.init();
//    }

}
