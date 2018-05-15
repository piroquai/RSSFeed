
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;



@SessionScoped @ManagedBean ("RSSctrl")
public class RSSControl {
    @PersistenceContext( unitName="MinimalApp")
    private EntityManager em;
    @Resource UserTransaction ut;

    /**
     * Read the list of all the rSSEntrys from the database.
     *
     * @return a list with all the RSSEntry instance found in the database.
     */
    public List<RSSEntry> getRSSEntrys() {
        return RSSEntry.retrieveAll( em);
    }

    /**
     * Update the reference object by setting its property values to match the one
     * existing in the database for the specific instance, identified by the
     * primary key value.
     *
     * @param rSSEntry
     *          the reference to the RSSEntry instance to be "loaded" from database.
     */
    public void refreshObject( RSSEntry rSSEntry) {
        RSSEntry foundRSSEntry = RSSEntry.retrieve( em, rSSEntry.getLink());
        rSSEntry.setTitle( foundRSSEntry.getTitle());
        rSSEntry.setDescription( foundRSSEntry.getDescription());
    }

    /**
     * Create and persist a new RSSEntry instance.
     *
     * @param link
     *          the link of the rSSEntry to create
     * @param title
     *          the title of the rSSEntry to create
     * @param description
     *          the description of the rSSEntry to create
     * @return a string representing the view name to display after finishing the
     *         execution of this method.
     */
    public String add( String link, String title, String description) {
        try {
            RSSEntry.add( em, ut, link, title, description);
            // Clear the form after creating the RSSEntry record
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            facesContext.getExternalContext().getRequestMap().remove( "rSSEntry");
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return "create";
    }

    /**
     * Update a RSSEntry instance.
     *
     * @param link
     *          the link of the rSSEntry to update (the rSSEntry will be identified in the
     *          database by using this value)
     * @param title
     *          the new value for the title property
     * @param description
     *          the new value for the description property
     * @return a string representing the view name to display after finishing the
     *         execution of this method.
     */
    public String update( String link, String title, String description) {
        try {
            RSSEntry.update( em, ut, link, title, description);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return "update";
    }

    /**
     * Delete a RSSEntry entry from database.
     *
     * @param link
     *          the link of the rSSEntry to delete - used to uniquely identify the
     *          rSSEntry entry.
     * @return a string representing the view name to display after finishing the
     *         execution of this method.
     */
//    public String destroy( String link) {
//        try {
//            RSSEntry.destroy( em, ut, link);
//        } catch ( Exception e) {
//            e.printStackTrace();
//        }
//        return "delete";
//    }

    /**
     * Clear/delete all entries from the <code>rSSEntrys</code> table
     *
     * @return a string representing the view name to display after finishing the
     *         execution of this method.
     *
     * @throws NotSupportedException
     * @throws SystemException
     * @throws IllegalStateException
     * @throws SecurityException
     * @throws HeuristicMixedException
     * @throws HeuristicRollbackException
     * @throws RollbackException
     */
//    public String clearData() {
//        try {
//            RSSEntry.cleardata( em, ut);
//        } catch ( Exception e) {
//            e.printStackTrace();
//        }
//        return "index";
//    }

    /**
     * Create test data (rows) in the <code>rSSEntrys</code> table
     *
     * @return a string representing the view name to display after finishing the
     *         execution of this method.
     *
     * @throws NotSupportedException
     * @throws SystemException
     * @throws IllegalStateException
     * @throws SecurityException
     * @throws HeuristicMixedException
     * @throws HeuristicRollbackException
     * @throws RollbackException
     */
//    public String createTestData() {
//        try {
//            RSSEntry.createTestData( em, ut);
//        } catch ( Exception e) {
//            e.printStackTrace();
//        }
//        return "index";
//    }
}