package code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import auth.ConfigBuilder;


public class Driver {
	
	private static final transient Logger LOG = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args)
	{
	//	LOG.info("Initializing...");
		LOG.debug("Creating Listener...");
	//	EnglishStatusListener listener = new EnglishStatusListener();
		LOG.debug("Creating Stream...");
	    TwitterStream twitterStream = new TwitterStreamFactory(ConfigBuilder.getConfig()).getInstance();
        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
          //  	User user = status.getUser();
        		//String profileLocation = user.getLocation();
        		
        		String tweetText = status.getText();
        		
        		long user_id = user.getId();
        		String timezone= user.getTimeZone();
        		int user_follower_count=user.getFollowersCount();
        		int user_favorite_count=user.getFavouritesCount();
        		String user_location = user.getLocation();
        			if ((user_location=="null")||(user_location.isEmpty())){
        				user_location="null";
        			}
        		int	status_favorite_count=status.getFavoriteCount();
        		int status_retweet_count = status.getRetweetCount();
        							
        		GeoLocation status_geolocation=status.getGeoLocation();
        		
        		
        		//Boolean Retweet =status.isRetweet();
        		
        		if(isEnglish(tweetText)) {
        	//		System.out.println(user_id+":::"+timezone+":::"+user_follower_count+":::"+user_favorite_count+":::"+user_location+":::"
           //     			+status_favorite_count+":::"+status_retweet_count+":::"+status_geolocation+":::"+tweetText);
        			LOG.info(user_id+":::"+timezone+":::"+user_follower_count+":::"+user_favorite_count+":::"+user_location+":::"
        			+status_favorite_count+":::"+status_retweet_count+":::"+status_geolocation+":::"+tweetText);
        		} 
                // gets Username
//                String username = status.getUser().getScreenName();
//                System.out.println(username);
//                String profileLocation = user.getLocation();
//                System.out.println(profileLocation);
//                long tweetId = status.getId(); 
//                System.out.println(tweetId);
//                String content = status.getText();
//                System.out.println(content +"\n");

            }
           
            private boolean isEnglish(String tweetText) {
				// TODO Auto-generated method stub
            	for(int i = 0;i < tweetText.length();i++) {
        			int c = tweetText.charAt(i);
        			if(c > 127) {
        				return false;
        			}
        	         
        		}
        		return true;
			}

			@Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        FilterQuery fq = new FilterQuery();
        
        String keywords[] = {"Netflix","GameOfThrones","Game of Thrones","hodor","khalisi","Star Wars: The Clone Wars",
        		"Star Wars","Clone wars","starwars","Disney","SWTOR","Dance Academy","DanceAcademyABC","DanceAcademy","dance",
        		"Mad Men","MadMen","PirateFairy","The Returned","TheReturned","JasonMott","Jason Mott","Jason","Breaking Bad",
        		"BreakingBad","Heisenberg","WalterWhite","Walter","Meth","BlueMeth","TrueDetective","TD_HBO","TrueDetectiveSeason2",
        		"HBO","Detective","Conaughey","imthecakeboss","cakeboss","Buddy Valastro","CakeBossBuddy","Black Sails","Netflix",
        		"Hulu","TVShows","tv","Movie","X-MEN","xmen","Avengers","Marvel","CaptainAmerica","wintersoldier","300","riseofanempire",
        		"rise of an empire","The lego movie","Lego","Legomovie","Muppets","MuppetsMostWanted","AmazingSpiderman2","spiderman",
        		"amazingspiderman","TheGrandBudapestHotel","Grand Budapest","noah","Transformers","Transformers Age of Extinction",
        		"GuardiansofGalaxy","NeedForSpeed"};

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq); 
        
	}
	

}
