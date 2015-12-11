/**
 * Created by Evan Almonte on 12/11/2015.
 */
import javax.swing.*;
import java.awt.*;

public class SudokuBoardView extends JFrame {
    private SudokuBoard theBoard;
    private JTextField outputField;
    private JRadioButton checkIfValidRadio;
    private JRadioButton enterRadio;
    private JRadioButton clearRadio;
    private JTextField inputField;
    private JButton [][] sudokuButtons;

    public SudokuBoardView(SudokuBoard theBoard, int height, int width) {
        super("Welcome To Sudoku");
        setSize(height, width);
        initComponents();
    }

    private void initComponents() {
        initOutputField();
        initRadioButtons();
        JPanel topContent = new JPanel(new BorderLayout());
        JPanel topContentOpts = new JPanel(new GridBagLayout());
        initTopPanel(topContent, topContentOpts);
        JPanel boardPanel = new JPanel(new GridLayout(theBoard.boardSize, theBoard.boardSize));
        initSudokuBtns(boardPanel);
        initInputField();
        add(boardPanel, BorderLayout.CENTER);
        add(topContent, BorderLayout.NORTH);
        add(inputField, BorderLayout.SOUTH);
    }

    private void initOutputField() {
        //Intentionally Left Empty.
    }
    private void initInputField() {
        //Intentionally Left Empty.
    }
    private void initRadioButtons() {
        //Intentionally Left Empty.
    }
    private void initTopPanel(JPanel panel, JPanel optionPanel) {
        //Intentionally Left Empty.
    }
    private void initSudokuBtns(JPanel boardPanel) {
        //Intentionally Left Empty.
    }


}
