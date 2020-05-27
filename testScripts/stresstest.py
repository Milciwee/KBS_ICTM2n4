import paramiko

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ip = "192.168.1.2"
try:
  ssh.connect(hostname=ip, username='root', password='Teamsvmware01!', port=3389)
  print("Connected")
except Exception as error:
  print("Connection failed")

print("Stressing CPU of " + ip + " for 20 seconds")
try:
  stdin, stdout, stderr = ssh.exec_command("sudo stress --cpu  2 --timeout 20")
except Exception as error:
  print("Test failed: command failed")

ssh.close()
print("Test succesfull: stresstest on CPU completed")