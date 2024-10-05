// MainActivity.kt
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.remaindeer.R
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var spinnerRepeat: Spinner
    private lateinit var btnDatePicker: Button
    private lateinit var btnTimePicker: Button
    private lateinit var btnSubmit: Button
    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTitle = findViewById(R.id.et_title)
        spinnerRepeat = findViewById(R.id.spinner_repeat)
        btnDatePicker = findViewById(R.id.btn_date_picker)
        btnTimePicker = findViewById(R.id.btn_time_picker)
        btnSubmit = findViewById(R.id.btn_submit)

        btnDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        btnTimePicker.setOnClickListener {
            showTimePickerDialog()
        }

        btnSubmit.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            btnDatePicker.text = selectedDate
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            btnTimePicker.text = selectedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun showConfirmationDialog() {
        val title = etTitle.text.toString()
        val repeat = spinnerRepeat.selectedItem.toString()

        val message = "Do you want to add this reminder?\n\nTitle: $title\nDate: $selectedDate\nTime: $selectedTime\nRepeat: $repeat"

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("SimpliRemind")
            .setMessage(message)
            .setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this, "Reminder added!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .create()

        alertDialog.show()
    }
}
