import {Request, Response} from "express";
import KcAdminClient from "keycloak-admin";

/**
 * POST /add
 * Create a new local account.
 */
export const postAdd = (req: Request, res: Response) => {
    const result = add();
    console.log('222222222222222222222', result)
    result.then(data => {
        console.log('demo test', data)
        res.render("home", {
            result: JSON.stringify(data),
            event: '1. users 2. add',
            error: ""
        });
    })
};
/**
 * get /find
 * Create a new local account.
 */

export const getFind = (req: Request, res: Response) => {
    const result = find();
    result.then(data => {
        console.log('data', data)
        res.render("home", {
            result: JSON.stringify(data),
            event: '1. users 2. find',
            error: ""
        });
    })
};

const kcAdminClient = new KcAdminClient();

async function add() {
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
    const result = await kcAdminClient.users.create({
        realm: 'spring-boot-quickstart',
        username: 'hukaijia',
        email: 'hukaiji@example.com',
    });
    return result;
}

async function find() {
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
    return await kcAdminClient.users.find();
}

process.on('unhandledRejection', (reason, promise) => {
    console.log('Unhandled Rejection:', reason)
    // 在这里处理
})


