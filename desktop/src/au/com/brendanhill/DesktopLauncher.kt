package au.com.brendanhill

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.formdev.flatlaf.FlatLightLaf
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.*


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
class DesktopLauncher : JFrame() {

    val theGame = Basic3DTest()

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        val container: Container = contentPane
        val canvas = LwjglAWTCanvas(theGame)

        canvas.canvas.setSize(800,800)

        val data = JTextArea("" )
        data.font = Font(Font.MONOSPACED, Font.PLAIN, 11)
        data.wrapStyleWord = false

        val toolbarPanel = JPanel()
        toolbarPanel.layout = FlowLayout()
        container.add(toolbarPanel, BorderLayout.NORTH)

        val addRandomPointButton = JButton("Add random point")
        toolbarPanel.add(addRandomPointButton, FlowLayout.LEFT)
        addRandomPointButton.addActionListener { repeat(2000) { theGame.addRandomPoint() } }


        val scroll = JScrollPane(data)
        scroll.horizontalScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        scroll.verticalScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED

        scroll.preferredSize = Dimension(400,200)
        container.add(scroll, BorderLayout.LINE_START)
        container.add(canvas.canvas, BorderLayout.CENTER)

        pack()
        isVisible = true
        setSize(1600, 800)
    }
}

fun main(arg: Array<String>) {

    FlatLightLaf.setup()
    SwingUtilities.invokeLater { DesktopLauncher() }
}