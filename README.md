All you need is to add layout, inherited from PinCodeLayout in xml

        ![Preview](https://github.com/geraldika/PinCodeLayout/blob/master/1ETfMLg0eI8.jpg)
 
 <com.carpe.quicknotes.presentation.QuickNotesPinCodeLayout
        android:id="@+id/pinCodeLayout"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:backgroundColor="@color/colorAccent"
        app:colorDigitTint="@color/colorBasePinCodeItemTint"
        app:colorFingerPrintTint="@color/colorAccent"
        app:drawableDigit="@drawable/background_digit_button"
        app:drawableDotActive="@drawable/background_pin_code_element_inactive"
        app:drawableDotInactive="@drawable/background_pin_code_element_active"
        app:drawableFingerPrint="@drawable/background_pin_code_element_active"
        app:iconEraser="@drawable/ic_eraser"
        app:iconFingerPrintDisable="@drawable/ic_fingerprint"
        app:iconFingerPrintEnable="@drawable/ic_fingerprint"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:maxDigits="4"
        app:titleText="@string/create_pin_code" />
        
        And override method initPresenterInstance() It makes you simple to use DI for injecting presenter
        
        
    
        
