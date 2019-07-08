import {Request, Response} from "express";
import KcAdminClient from "keycloak-admin";

/**
 * get /find
 * Create a new local account.
 */

export const getToken = (req: Request, res: Response) => {
    const result = getAccessToken();
    result.then(data => {
        console.log('data', data)
        res.render("home", {
            result: JSON.stringify(data),
            event: '1. users 2. token',
            error: ""
        });
    })
};

const kcAdminClient = new KcAdminClient();

async function getAccessToken() {
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
    return await kcAdminClient.getAccessToken();
}

process.on('unhandledRejection', (reason, promise) => {
    console.log('Unhandled Rejection:', reason)
    // 在这里处理
})


