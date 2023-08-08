package com.lawzoom.complianceservice.dao.reminderDao;

import com.lawzoom.complianceservice.model.complianceModel.Compliance;
import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.model.reminderModel.Reminder;

public interface ReminderDao {
    Reminder findReminderByComplianceOrTaskOrSubTask(Compliance compliance, ComplianceTask complianceTask, ComplianceSubTask complianceSubTask);

    Reminder saveReminder(Reminder reminder);

    Reminder findReminderByComplianceOrTaskOrSubTaskAndIdNot(Compliance compliance, ComplianceTask complianceTask, ComplianceSubTask complianceSubTask, Long id);

    Reminder findReminderById(Long id);

    boolean deleteReminder(Reminder reminder);

    Reminder updateReminder(Reminder reminder);
}
