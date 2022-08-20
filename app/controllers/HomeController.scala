package controllers

import javax.inject._
import play.api._
import play.api.libs.json.Json
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  //nueva funcion para ver que todo funciona
  def ping() = Action {
    implicit request: Request[AnyContent] => Ok("String works")
  }

  //el guión bajo es para indicar que no usaremos el request como tal, si no lo que sea que venga
  def anotherOne() = Action {
    _ => Ok(Json.obj("yes" -> true))
  }

  //otro metodo donde nos interesa lo que venga
  def nameParam(name: String) = Action {
    implicit request: Request[AnyContent] => Ok(Json.obj("name" -> name))
  }

  //funcoin donde recibimos un cuerpo en formato json... para este usamos postman
  def posted() = Action(parse.json) {
    implicit request =>
      Ok(Json.obj("received" -> Json.toJson(request.body)))
  }
}
