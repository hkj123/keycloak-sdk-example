import {Request, Response, NextFunction} from "express";
import KcAdminClient from "keycloak-admin";

/**
 * POST /add
 * Create a new local account.
 */
export const postAdd = (req: Request, res: Response, next: NextFunction) => {
    addUser();
    res.render("home", {
        title: "Home"
    });
};
const kcAdminClient = new KcAdminClient();

async function addUser() {
    kcAdminClient.setConfig({
        realmName: "spring-boot-quickstart"
    });

    await kcAdminClient.auth({
        username: "alice",
        password: "password",
        grantType: "password",
        clientId: "app-authz-springboot",
        clientSecret: "46cf15ff-48ca-429c-926d-0707fdf62cb0"
    });

    const a = await kcAdminClient.users.create({
        realm: 'spring-boot-quickstart',
        username: 'hukaijia',
        email: 'hukaiji@example.com',
    });
    console.log(a);
}

