package au.com.brendanhill

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.formdev.flatlaf.FlatLightLaf
import java.awt.BorderLayout
import java.awt.Container
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.SwingUtilities


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
class DesktopLauncher : JFrame() {

    init {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        val container: Container = getContentPane()
        val canvas = LwjglAWTCanvas(Basic3DTest())
        container.add(JButton("test button"), BorderLayout.WEST)
        container.add(canvas.getCanvas(), BorderLayout.CENTER)
        pack()
        setVisible(true)
        setSize(800, 600)
    }
}

fun main(arg: Array<String>) {

    FlatLightLaf.setup()
    SwingUtilities.invokeLater { DesktopLauncher() }
}