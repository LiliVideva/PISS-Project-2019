package bg.sofia.uni.fmi.piss.project.controller;

public class Routes {
    private static final String REDIRECT = "redirect:";
    private static final String HOME_PAGE = "/index";

    static String redirect(String path) {
        return REDIRECT + path;
    }

    static String redirect(String path, Long resourceId) {
        return String.format(REDIRECT + path, resourceId);
    }

    public static String homePage() {
        return redirect(HOME_PAGE);
    }
}
