import * as express from "express";
import * as path from "path";

// Create Express server
let app = express();

// Controllers (route handlers)
import * as homeController from "./controllers/home";
import * as userController from "./controllers/user";
import * as errorController from "./controllers/error";

// Express configuration
app.set('views', path.join(__dirname, '../views'))// 设置存放模板文件的目录
app.set('view engine', 'ejs')// 设置模板引擎为 ejs


/**
 * Primary app routes.
 */
app.get("/", homeController.index);
app.post("/add", userController.postAdd);
app.get("/error", errorController.error);

app.listen(3000, () => {
  console.log("server is running port 3000");
});

export = app;
