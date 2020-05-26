import paramiko

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
try:
  ssh.connect(hostname='192.168.1.2', username='root', password='Teamsvmware01!', port=3389)
  print("Connected")
except Exception as error:
  print("Connection failed")

try:
  stdin, stdout, stderr = ssh.exec_command("systemctl reboot")
except Exception as error:
  print("Test failed: command failed")

ssh.close()
print("Test succesfull: server rebooted")