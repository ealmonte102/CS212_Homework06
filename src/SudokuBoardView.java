/**
 * Created by Evan Almonte on 12/11/2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        outputField = new JTextField(20);
        outputField.setEditable(false);
        outputField.setText("System Output");
        outputField.setHorizontalAlignment((int)CENTER_ALIGNMENT);
    }
    private void initInputField() {
        inputField = new JTextField(20);
        inputField.setEditable(true);
        inputField.setHorizontalAlignment((int)CENTER_ALIGNMENT);
    }
    private void initRadioButtons() {
        ButtonGroup radioOptionGroup= new ButtonGroup();
        checkIfValidRadio = new JRadioButton("Validate Move", true);
        enterRadio = new JRadioButton("Enter Move");
        clearRadio = new JRadioButton("Clear Board");

        radioOptionGroup.add(checkIfValidRadio);
        radioOptionGroup.add(enterRadio);
        radioOptionGroup.add(clearRadio);
    }
    private void initTopPanel(JPanel topPanel, JPanel optionPanel) {
        topPanel.add(outputField, BorderLayout.NORTH);
        topPanel.add(optionPanel,BorderLayout.SOUTH);
        topPanel.setSize(getSize());
        optionPanel.add(checkIfValidRadio);
        optionPanel.add(enterRadio);
        optionPanel.add(clearRadio);
    }
    private void initSudokuBtns(JPanel boardPanel) {
        sudokuButtons = new JButton[4][4];
        for(int i = 0; i < theBoard.boardSize; i++) {
            for(int j = 0; j < theBoard.boardSize; j++) {
                sudokuButtons[i][j] = new JButton();
                String boardValue = Integer.toString(theBoard.getSquare(i,j).getValue());
                if(boardValue == "0") { boardValue = ""; }
                else { sudokuButtons[i][j].setText(boardValue); }
                sudokuButtons[i][j].setActionCommand(i + "," + j);
                boardPanel.add(sudokuButtons[i][j]);
            }
        }
    }

    public void addClearRadioListener(ActionListener crl) {
        clearRadio.addActionListener(crl);
    }

    public void addButtonListener(ActionListener bl, int row, int col) {
        sudokuButtons[row][col].addActionListener(bl);
    }

    public void setButtonText(String text, int row, int col) {
        sudokuButtons[row][col].setText(text);
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void setOutputText(String textOutput) {
        outputField.setText(textOutput);
    }

    public void resetRadioSelection() {
        checkIfValidRadio.setSelected(true);
    }
}
