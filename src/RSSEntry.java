import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;

import javax.persistence.*;
import javax.transaction.UserTransaction;


@Entity @Table( name="rssentrys")
@RequestScoped @ManagedBean( "rssentry")
public class RSSEntry {
//    @Id @GeneratedValue
//    private int idRSSEntrys;
   @Id private String link;
    private String title;
    private String description;

    /**
     * Default constructor, required for entity classes
     */
    public RSSEntry() {
    }

    /**
     * Constructor
     */
    public RSSEntry(String title, String link, String description) {
        this.setLink(link);
        this.setTitle(title);
        this.setDescription(description);
    }

    /**
     * Getters and setters
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    /**
     * Create a human readable serialization.
     */
    public String toString() {
        return "{ link: '" + this.link + "', title:'" + this.title + "', description: '"
                + this.description + "'}";
    }

    /**
     * Retrieve all RSSEntry records from the RSSEntrys table.
     *
     * @param em reference to the entity manager
     * @return the list of all RSSEntry records
     */
    public static List<RSSEntry> retrieveAll(EntityManager em) {
        TypedQuery<RSSEntry> query = em.createQuery("SELECT b FROM RSSEntry b", RSSEntry.class);
        List<RSSEntry> RSSEntrys = query.getResultList();
        System.out.println("RSSEntry.retrieveAll: " + RSSEntrys.size()
                + " RSSEntrys were loaded from DB.");
        return RSSEntrys;
    }

    /**
     * Retrieve a RSSEntry record from the RSSEntrys table.
     *
     * @param em   reference to the entity manager
     * @param link the RSSEntry's link
     * @return the RSSEntry with the given link
     */
    public static RSSEntry retrieve(EntityManager em, String link) {
        RSSEntry RSSEntry = em.find(RSSEntry.class, link);
        if (RSSEntry != null) {
            System.out.println("RSSEntry.retrieve: loaded RSSEntry " + RSSEntry);
        }
        return RSSEntry;
    }

    /**
     * Create a RSSEntry instance.
     *
     * @param em    reference to the entity manager
     * @param ut    reference to the user transaction
     * @param link  the link of the RSSEntry to create
     * @param title the title of the RSSEntry to create
     * @param description  the description of the RSSEntry to create
     * @throws Exception
     */
    public static void add(EntityManager em, UserTransaction ut, String link,
                           String title, String description) throws Exception {
        ut.begin();
        RSSEntry RSSEntry = new RSSEntry(link, title, description);
        em.persist(RSSEntry);
        ut.commit();
        System.out.println("RSSEntry.add: the RSSEntry " + RSSEntry + " was saved.");
    }

    /**
     * Update a RSSEntry instance
     *
     * @param em    reference to the entity manager
     * @param ut    reference to the user transaction
     * @param link  the link of the RSSEntry to update
     * @param title the title of the RSSEntry to update
     * @param description  the description of the RSSEntry to update
     * @throws Exception
     */
    public static void update(EntityManager em, UserTransaction ut, String link,
                              String title, String description) throws Exception {
        ut.begin();
        RSSEntry RSSEntry = em.find(RSSEntry.class, link);
        if (title != null && !title.equals(RSSEntry.title)) {
            RSSEntry.setTitle(title);
        }
        if (description != RSSEntry.description) {
            RSSEntry.setDescription(description);
        }
        ut.commit();
        System.out.println("RSSEntry.update: the RSSEntry " + RSSEntry + " was updated.");
    }
}

