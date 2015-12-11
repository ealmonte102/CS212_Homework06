/**
 * Created by Evan Almonte on 12/11/2015.
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class SudokuBoardController {
    private SudokuBoard boardModel;
    private SudokuBoardView boardView;
    public SudokuBoardController(SudokuBoard model, SudokuBoardView view) {
        boardModel = model;
        boardView = view;
        view.addClearRadioListener(new ClearRadioListener());
    }

    private class ClearRadioListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int userChoice = JOptionPane.showConfirmDialog(null, "Would you like to reset the board?",
                    "Please Choose An Option", JOptionPane.YES_NO_OPTION);
            if(userChoice == JOptionPane.YES_OPTION) {
                boardModel.reset();
                for(int i = 0; i < boardModel.boardSize; ++i) {
                    for(int j = 0; j < boardModel.boardSize; ++j) {
                        String boardValue = Integer.toString(boardModel.getSquare(i,j).getValue());
                        if(boardValue == "0") { boardValue = ""; }
                        boardView.setButtonText(boardValue, i, j);
                    }
                }
            }
            boardView.resetRadioSelection();
        }
    }
}
