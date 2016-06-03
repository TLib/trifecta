package com.github.ldaniels528.trifecta.sjs.services

import com.github.ldaniels528.meansjs.angularjs.Service
import com.github.ldaniels528.meansjs.angularjs.http.Http
import com.github.ldaniels528.meansjs.core.browser.encodeURI
import com.github.ldaniels528.meansjs.util.ScalaJsHelper._
import com.github.ldaniels528.trifecta.sjs.models.{Message, PublishMessageResponse}

import scala.concurrent.ExecutionContext
import scala.scalajs.js

/**
  * Message Data Service
  * @author lawrence.daniels@gmail.com
  */
class MessageDataService($http: Http) extends Service {

  def getMessage(topic: String, partition: Int, offset: Int)(implicit ec: ExecutionContext) = {
    $http.get[Message](s"/api/message_data/$topic/$partition/$offset") map (_.data)
  }

  def getMessageKey(topic: String, partition: Int, offset: Int)(implicit ec: ExecutionContext) = {
    $http.get[Message](s"/api/message_key/$topic/$partition/$offset") map (_.data)
  }

  def publishMessage(topic: String, key: String, message: String, keyFormat: String, messageFormat: String)(implicit ec: ExecutionContext) = {
    $http.post[PublishMessageResponse](
      url = s"/api/message/${encodeURI(topic)}",
      headers = js.Dictionary("Content-Type" -> "application/json"),
      data = js.Dictionary(
        "key" -> key,
        "message" -> message,
        "keyFormat" -> keyFormat,
        "messageFormat" -> messageFormat)) map (_.data)
  }

}
