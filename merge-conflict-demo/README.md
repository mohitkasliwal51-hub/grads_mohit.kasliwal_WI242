# Merge Conflict Demo

This assignment demonstrates how merge conflicts occur in Git.

Steps performed:
1. Created two branches.
2. Modified same line in both branches.
3. Merged branches into main.
4. Resolved conflict manually.

#text in the file which got conflicted
<<<<<<< HEAD
welcome from branch 1
=======
welcome from branch 2
>>>>>>> branch2


#commands and their output
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main)
$ echo "namaste duniya" > file.txt
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main)
$ git commit -m "initial commit to demonstrate merge conflict in github"
On branch main
Your branch is up to date with 'origin/main'.
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   ../assignment-student-web-page/pom.xml
        modified:   ../assignment-student-web-page/src/main/java/com/mohit/assignment_student_web_page/config/H2Config.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/MainApp.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/admin/AdminMenu.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/config/MongoConnection.java        
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/dao/Admin.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/dao/Maintenance.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/dao/Owner.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/dao/Site.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/dbOperation/AdminDAO.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/dbOperation/MaintenanceDAO.java    
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/dbOperation/OwnerDAO.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/dbOperation/SiteDAO.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/model/Admin.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/model/Maintenance.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/model/Owner.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/model/Site.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/owner/OwnerMenu.java
        deleted:    ../layout-mentainance-app/src/main/java/com/layout/setup/CreateCollections.java       
        deleted:    ../scheduler/src/main/java/com/example/scheduler/Scheduler.java
        deleted:    ../scheduler/src/main/java/com/example/scheduler/SchedulerApplication.java
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        ./
no changes added to commit (use "git add" and/or "git commit -a")
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main)      
$ git add .
warning: in the working copy of 'merge-conflict-demo/file.txt', LF will be replaced by CRLF the next time Git touches it
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main)      
$ git commit -m "initial commit to demonstrate merge conflict in github"
[main f2f9703] initial commit to demonstrate merge conflict in github
 1 file changed, 1 insertion(+)
 create mode 100644 merge-conflict-demo/file.txt
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main)      
$ git checkout -b branch1
Switched to a new branch 'branch1'
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (branch1)   
$ git add .
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (branch1)   
$ git commit -m "updated file in branch"
[branch1 be15e06] updated file in branch
 1 file changed, 1 insertion(+), 1 deletion(-)
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (branch1)   
$ git checkout -b main
fatal: a branch named 'main' already exists
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (branch1)   
$ git checkout main
M       assignment-student-web-page/pom.xml
M       assignment-student-web-page/src/main/java/com/mohit/assignment_student_web_page/config/H2Config.java
D       layout-mentainance-app/src/main/java/com/layout/MainApp.java
D       layout-mentainance-app/src/main/java/com/layout/admin/AdminMenu.java
D       layout-mentainance-app/src/main/java/com/layout/config/MongoConnection.java
D       layout-mentainance-app/src/main/java/com/layout/dao/Admin.java
D       layout-mentainance-app/src/main/java/com/layout/dao/Maintenance.java
D       layout-mentainance-app/src/main/java/com/layout/dao/Owner.java
D       layout-mentainance-app/src/main/java/com/layout/dao/Site.java
D       layout-mentainance-app/src/main/java/com/layout/dbOperation/AdminDAO.java
D       layout-mentainance-app/src/main/java/com/layout/dbOperation/MaintenanceDAO.java
D       layout-mentainance-app/src/main/java/com/layout/dbOperation/OwnerDAO.java
D       layout-mentainance-app/src/main/java/com/layout/dbOperation/SiteDAO.java
D       layout-mentainance-app/src/main/java/com/layout/model/Admin.java
D       layout-mentainance-app/src/main/java/com/layout/model/Maintenance.java
D       layout-mentainance-app/src/main/java/com/layout/model/Owner.java
D       layout-mentainance-app/src/main/java/com/layout/model/Site.java
D       layout-mentainance-app/src/main/java/com/layout/owner/OwnerMenu.java
D       layout-mentainance-app/src/main/java/com/layout/setup/CreateCollections.java
D       scheduler/src/main/java/com/example/scheduler/Scheduler.java
D       scheduler/src/main/java/com/example/scheduler/SchedulerApplication.java
Switched to branch 'main'
Your branch is ahead of 'origin/main' by 1 commit.
  (use "git push" to publish your local commits)
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main)      
$ git checkout -b branch2
Switched to a new branch 'branch2'
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (branch2)   
$ git add .
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (branch2)   
$ git commit "updated file in branch2"
error: pathspec 'updated file in branch2' did not match any file(s) known to git
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (branch2)   
$ git commit -m "updated file in branch2"
[branch2 005ebf5] updated file in branch2
 1 file changed, 1 insertion(+), 1 deletion(-)
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (branch2)   
$ git checkout main
M       assignment-student-web-page/pom.xml
M       assignment-student-web-page/src/main/java/com/mohit/assignment_student_web_page/config/H2Config.java
D       layout-mentainance-app/src/main/java/com/layout/MainApp.java
D       layout-mentainance-app/src/main/java/com/layout/admin/AdminMenu.java
D       layout-mentainance-app/src/main/java/com/layout/config/MongoConnection.java
D       layout-mentainance-app/src/main/java/com/layout/dao/Admin.java
D       layout-mentainance-app/src/main/java/com/layout/dao/Maintenance.java
D       layout-mentainance-app/src/main/java/com/layout/dao/Owner.java
D       layout-mentainance-app/src/main/java/com/layout/dao/Site.java
D       layout-mentainance-app/src/main/java/com/layout/dbOperation/AdminDAO.java
D       layout-mentainance-app/src/main/java/com/layout/dbOperation/MaintenanceDAO.java
D       layout-mentainance-app/src/main/java/com/layout/dbOperation/OwnerDAO.java
D       layout-mentainance-app/src/main/java/com/layout/dbOperation/SiteDAO.java
D       layout-mentainance-app/src/main/java/com/layout/model/Admin.java
D       layout-mentainance-app/src/main/java/com/layout/model/Maintenance.java
D       layout-mentainance-app/src/main/java/com/layout/model/Owner.java
D       layout-mentainance-app/src/main/java/com/layout/model/Site.java
D       layout-mentainance-app/src/main/java/com/layout/owner/OwnerMenu.java
D       layout-mentainance-app/src/main/java/com/layout/setup/CreateCollections.java
D       scheduler/src/main/java/com/example/scheduler/Scheduler.java
D       scheduler/src/main/java/com/example/scheduler/SchedulerApplication.java
Switched to branch 'main'
Your branch is ahead of 'origin/main' by 1 commit.
  (use "git push" to publish your local commits)
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main)      
$ git merge branch1
Updating f2f9703..be15e06
Fast-forward
 merge-conflict-demo/file.txt | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main)      
$ git merge branch2
warning: in the working copy of 'assignment-student-web-page/pom.xml', LF will be replaced by CRLF the next time Git touches it
Auto-merging merge-conflict-demo/file.txt
CONFLICT (content): Merge conflict in merge-conflict-demo/file.txt
Automatic merge failed; fix conflicts and then commit the result.
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main|MERGING)
$ git commit -m "resolved merge conflict"
U       merge-conflict-demo/file.txt
error: Committing is not possible because you have unmerged files.
hint: Fix them up in the work tree, and then use 'git add/rm <file>'
hint: as appropriate to mark resolution and make a commit.
fatal: Exiting because of an unresolved conflict.
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main|MERGING)
$ git add file.txt
Wissen@PF59HK4X MINGW64 ~/Desktop/java training/grad_mohit.kasliwal_WI242/merge-conflict-demo (main|MERGING)
$ git commit -m "resolved merge conflict"
[main cfb3f9d] resolved merge conflict
