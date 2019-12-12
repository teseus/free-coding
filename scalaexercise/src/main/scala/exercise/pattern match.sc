def matching(x:Int):String = x match {
  case 1 => "One"
  case 2 => "Two"
  case 3 => "Three"
  case _ => "Many"
}

println(matching(1))
println(matching(2))
println(matching(3))
println(matching(4))
println(matching('a'))

abstract class Notification

case class Email(sender:String, title:String, body:String) extends Notification
case class SMS(caller:String, message:String) extends Notification
case class VoiceRecording(contractName: String, link: String) extends Notification

def showNotification(notification: Notification):String = {
  notification match {
    case Email(sender, title, _) =>
      s"you got an email from $sender with title: $title"
    case SMS(number, message) =>
      s"you got sms from $number message $message"
    case VoiceRecording(name, link) =>
      s"you got voice message from $name. click to link $link"
  }
}

val email = Email("to@gmail.com", "Help Me", "I have a Question")
val someSms = SMS("12345", "Are you there?")
val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")

println(showNotification(email))
println(showNotification(someSms))
println(showNotification(someVoiceRecording))