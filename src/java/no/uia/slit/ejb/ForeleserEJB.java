//package no.uia.slit.ejb;
//
//import no.uia.slit.entity.Foreleser;
//import java.util.ArrayList;
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
///**
// *
// * @author Tor
// */
//@Stateless
//public class ForeleserEJB extends AbstractFacade<Foreleser> {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public ForeleserEJB() {
//        super(Foreleser.class);
//    }
//
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
//
//    public List<Foreleser> findUnassigned() {
//        List<Foreleser> all = findAll();
//        List<Foreleser> unassigned = new ArrayList<Foreleser>();
//
//        for (Foreleser foreleser : all) {
//            if (foreleser.getEvaluering().isEmpty()) {
//                unassigned.add(foreleser);
//                System.err.println("Added " + foreleser);
//
//            }
//        }
//        return unassigned;
//    }
//
//    /**
//     * Update is a bit more complex, because the old and new departments must
//     * both be updated. In addition we need to get managed objects to ensure
//     * that everything propagates to the database
//     * @param editedForeleser
//     * @return 
//     */
//    @Override
//    public Foreleser update(Foreleser editedForeleser) {
//        Foreleser dbForeleser = find(editedForeleser.getId());
//        if (null == dbForeleser) {
//            insert(editedForeleser);
//        }
//        editedForeleser = super.update(editedForeleser);
//        return editedForeleser;
//    }
//}
