import * as express from "express";
import * as path from "path";

// Create Express server
let app = express();

// Controllers (route handlers)
import * as homeController from "./controllers/home";
import * as userController from "./controllers/user";
import * as errorController from "./controllers/error";
import * as tokenController from "./controllers/token";
import * as realmController from "./controllers/realm";

// Express configuration
app.set('views', path.join(__dirname, '../views'))// 设置存放模板文件的目录
app.set('view engine', 'ejs')// 设置模板引擎为 ejs

app.use(
    express.static(path.join(__dirname, "public"), { maxAge: 31557600000 })
);
/**
 * Primary app routes.
 */
app.get("/", homeController.index);
app.post("/users/add", userController.postAdd);
app.get("/users/find", userController.getFind);
app.get("/token", tokenController.getToken);
app.get("/error", errorController.error);
app.get("/realm", realmController.getRealm);


app.listen(3000, () => {
  console.log("server is running port 3000");
});
console.log(123)
export = app;
