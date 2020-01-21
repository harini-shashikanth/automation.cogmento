$windowHandle = WinWait("File Upload", "", 3) ; 3 second timeout - NB the window name will be different in different browsers!

If $windowHandle == 0 Then
   MsgBox(0, "", "Upload popup not found")
Else
   ;MsgBox(0, "", "Upload popup found: " & $windowHandle)
   WinActivate($windowHandle)
   Send("C:\Users\shashikanth\git\automation.cogmento\testdata\img\origin_usa_inc.jpg")
   Send("{ENTER}")
EndIf