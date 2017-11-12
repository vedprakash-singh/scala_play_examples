package controllers

import java.util.Date
import javax.inject._

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.data.validation.Constraints._
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
    def doLogin = Action { implicit request =>
      val loginRequest = loginForm.bindFromRequest.get
      Ok(s"amount: '${loginRequest.amount}',"+
        s"name: '${loginRequest.name}',"+
        s"cardNo: '${loginRequest.cardNo}',"+
        s"cvvNo: '${loginRequest.cvvNo}',"

      )
    }

    def loginForm = Form(mapping(
      "amount" -> number,
      "name" -> text,
      "cardNo" -> number,
      "cvvNo" -> number)
    (PaymentForm.apply)(PaymentForm.unapply))
  }
  case class PaymentForm( amount:Int,
                          name:String,
                          cardNo: Int,
                          cvvNo: Int)

