package com.trivia.question;

import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.*;
import com.filenet.api.util.UserContext;
import com.ibm.casemgmt.api.Case;
import com.ibm.casemgmt.api.CaseType;
import com.ibm.casemgmt.api.constants.ModificationIntent;
import com.ibm.casemgmt.api.context.CaseMgmtContext;
import com.ibm.casemgmt.api.context.P8ConnectionCache;
import com.ibm.casemgmt.api.context.SimpleP8ConnectionCache;
import com.ibm.casemgmt.api.context.SimpleVWSessionCache;
import com.ibm.casemgmt.api.objectref.ObjectStoreReference;
import com.ibm.casemgmt.api.properties.CaseMgmtProperties;
import com.trivia.question.business.Constants;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CaseManagerConnector {
    UserContext uc;
    public ObjectStore getObjectStore() {
        // Set the constants
// Note: use /wsi/FNCEWS40MTOM/ for CEWS transport
        String uri = Constants.CE_URI;
        String username = Constants.USERID;
        String password = Constants.PASSWORD;
        P8ConnectionCache connCache = new SimpleP8ConnectionCache();
        Connection conn = connCache.getP8Connection(uri);
// Get the user context
         uc =UserContext.get();
// Build the subject using the FileNetP8WSI stanza
// Use the FileNetP8WSI stanza for CEWS
        Subject subject = UserContext.createSubject(conn, username, password,"FileNetP8WSI");
        uc.pushSubject(subject);


        CaseMgmtContext origCmctx =
                CaseMgmtContext.set(
                        new CaseMgmtContext(
                                new SimpleVWSessionCache(), connCache
                        )
                );
// Get the default domain
            Domain domain = Factory.Domain.getInstance(conn, null);
// Get an object store
            return Factory.ObjectStore.fetchInstance(domain,
                    Constants.OBJECT_STORE_NAME, null);

    }
    public void createReviewQuizCase(int userId, int quizId, List<String> questions, List<String> answers){
        try {
            ObjectStoreReference objectStoreReference = new ObjectStoreReference(getObjectStore());
            System.out.println("getObjectStore completed");
            CaseType caseTypeObj = CaseType.fetchInstance(objectStoreReference, "QRH_ReviewQuiz");
            System.out.println("CaseType.fetchInstance completed");
            Case newCase = Case.createPendingInstanceFetchDefaults(caseTypeObj);
            System.out.println("createPendingInstanceFetchDefaults completed");
            CaseMgmtProperties props = newCase.getProperties();
            HashMap<String,Object> vals = new HashMap<>();
            vals.put("QRH_quizid", quizId);
            vals.put("QRH_userid", userId);
            vals.put("QRH_questions", questions);
            vals.put("QRH_answers", answers);
            if (props != null && vals != null) {
                for (String symbolicName : vals.keySet()) {
                    if (props.supportsProperty(symbolicName)) {
                        props.putObjectValue(symbolicName, vals.get(symbolicName));
                    } else {
                        System.err.println("cannot find property symbolic name (" + symbolicName + ") in case ");
                    }
                }
            }
            newCase.save(RefreshMode.REFRESH, null, ModificationIntent.NOT_MODIFY);
            System.out.println(newCase.getProperties().asList().toString());
            System.out.println("case created");
        }
        catch(Exception e){
            throw e;
        }
        finally {
            uc.popSubject();
        }
    }


}
