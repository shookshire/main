package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.CommandNotAvaiableInActiveViewException;
import seedu.address.model.person.Category;
import seedu.address.model.person.Client;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.ui.util.ListPanelController;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class RestoreCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "restore";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " a active tutor or student";

    public static final String MESSAGE_RESTORE_STUDENT_SUCCESS = "Student restored: %1$s";
    public static final String MESSAGE_CLOSE_TUTOR_SUCCESS = "Tutor restored: %1$s";

    private final Index targetIndex;
    private final Category category;

    private Client clientToRestore;

    public RestoreCommand(Index targetIndex, Category category) {
        this.targetIndex = targetIndex;
        this.category = category;
    }

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(clientToRestore);
        try {
            model.deleteClosedClient(clientToRestore, category);
        } catch (PersonNotFoundException pnfe) {
            throw new AssertionError("The target client cannot be missing");
        }

        try {
            if(category.isStudent()) {
                model.addStudent(clientToRestore);
            } else {
                model.addTutor(clientToRestore);
            }
        } catch (DuplicatePersonException e) {
            throw new AssertionError("The client should not be duplicated");
        }

        if(category.isStudent()) {
            return new CommandResult(String.format(MESSAGE_RESTORE_STUDENT_SUCCESS, clientToRestore));
        } else {
            return new CommandResult(String.format(MESSAGE_CLOSE_TUTOR_SUCCESS, clientToRestore));
        }
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        if(ListPanelController.isCurrentDisplayActiveList()) {
            throw new CommandNotAvaiableInActiveViewException();
        }

        List<Client> lastShownList;

        if (category.isStudent()) {
            lastShownList = model.getFilteredClosedStudentList();
        } else {
            lastShownList = model.getFilteredClosedTutorList();
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        clientToRestore = lastShownList.get(targetIndex.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RestoreCommand // instanceof handles nulls
                && this.targetIndex.equals(((RestoreCommand) other).targetIndex) // state check
                && Objects.equals(this.clientToRestore, ((RestoreCommand) other).clientToRestore));
    }
}
