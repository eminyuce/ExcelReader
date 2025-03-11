package com.excel.reader.util;


import org.springframework.http.HttpHeaders;

/**
 * This will need to revisited when we found if/what string the employee id is
 * being put in the header
 */
public final class LogUtil {

    LogUtil() {

    }

    private static final String TRANS_ID = "TRANS_ID";

    private static final String OBLIX_UID = "OBLIX_UID";

    private static final String USER_ID = "userId";

    public static String getUser(HttpHeaders hdrs) {
        String requestor = hdrs.getFirst(OBLIX_UID);
        if (requestor == null || requestor.trim().isEmpty()) {
            requestor = hdrs.getFirst(USER_ID);
        }
        if (requestor == null || requestor.trim().isEmpty()) {
            requestor = getUser();
        }
        return requestor;

    }

    public static String getUser() {
        String user = "";

        //var authentication = SecurityContextHolder.getContext().getAuthentication();

        // Map<String, Object> attributes = null;
        // if (authentication instanceof OAuth2AuthenticationToken) {
        // attributes = ((OAuth2AuthenticationToken)
        // authentication).getPrincipal().getAttributes();
        // } else if (authentication instanceof JwtAuthenticationToken) {
        // attributes = ((JwtAuthenticationToken) authentication).getTokenAttributes();
        // }
        // System.out.println(attributes.toString());

//        if (authentication != null) {
//            String usernameAttr = authentication.getName();
//            int indexNum = usernameAttr.indexOf('@');
//            if (indexNum > -1) {
//                user = usernameAttr.substring(0, indexNum);
//            } else {
//                user = usernameAttr;
//            }
//        }
        // adding this so it looks okay during testing
        if (user.isEmpty()) {
            user = "Swagger";
        }
        return user;
    }

}
