import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

public class ReadTest {
	@PersistenceContext( unitName="MinimalApp")
	private static EntityManager em;
	@Resource
	static UserTransaction ut;
	public static void main(String[] args) {

		RSSFeedParser parser = new RSSFeedParser(
				"http://www.vogella.com/article.rss");
		RSSEntry Entry =new RSSEntry();
		Feed feed = parser.readFeed();
		System.out.println(feed);
		for (FeedMessage message : feed.getMessages()) {

			try {
				RSSEntry.add(em,ut, feed.link,feed.title,feed.description );
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(message);

		}

	}
}
