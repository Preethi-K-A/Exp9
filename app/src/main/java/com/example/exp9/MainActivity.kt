import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exp9.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSend = findViewById<Button>(R.id.buttonSend)
        val editTextRecipient = findViewById<EditText>(R.id.editTextRecipient)
        val editTextSubject = findViewById<EditText>(R.id.editTextSubject)
        val editTextMessage = findViewById<EditText>(R.id.editTextMessage)

        buttonSend.setOnClickListener {
            val recipient = editTextRecipient.text.toString()
            val subject = editTextSubject.text.toString()
            val message = editTextMessage.text.toString()

            if (recipient.isNotEmpty() && subject.isNotEmpty() && message.isNotEmpty()) {
                sendEmail(recipient, subject, message)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendEmail(recipient: String, subject: String, message: String) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "message/rfc822"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        } catch (e: Exception) {
            Toast.makeText(this, "Error sending email", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
