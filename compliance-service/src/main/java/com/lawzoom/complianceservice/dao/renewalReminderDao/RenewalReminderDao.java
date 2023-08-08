package com.lawzoom.complianceservice.dao.renewalReminderDao;

import com.lawzoom.complianceservice.model.complianceSubTaskModel.ComplianceSubTask;
import com.lawzoom.complianceservice.model.complianceTaskModel.ComplianceTask;
import com.lawzoom.complianceservice.model.renewalModel.RenewalReminder;

public interface RenewalReminderDao {
    RenewalReminder findRenewalReminderByComplianceTaskOrSubTask(ComplianceTask complianceTask, ComplianceSubTask complianceSubTask);

    RenewalReminder saveRenewalReminder(RenewalReminder renewalReminder);

    RenewalReminder updateRenewalReminder(RenewalReminder renewalReminder);

    RenewalReminder findRenewalReminderByComplianceTaskOrSubTaskAndIdNot(ComplianceTask complianceTask, ComplianceSubTask complianceSubTask, Long id);

    RenewalReminder findRenewalReminderById(Long id);

    boolean deleteRenewalReminder(RenewalReminder renewalReminder);
}
