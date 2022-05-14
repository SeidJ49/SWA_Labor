package de.hse.swa.jodel.orm.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class VotingDao {

    @Inject
    EntityManager em;
    
    @Inject
    CommentDao commentDao;


}
