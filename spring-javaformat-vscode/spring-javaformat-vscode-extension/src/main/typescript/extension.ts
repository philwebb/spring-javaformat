import * as vscode from 'vscode'

export function activate(context: vscode.ExtensionContext) {
  console.log('Congratulations, your extension "mrphil" is now active!')
  let disposable = vscode.commands.registerCommand('mrphil.helloWorld', () => {
    vscode.window.showInformationMessage('Hello World from TEST!')
  })
  context.subscriptions.push(disposable)
}

export function deactivate() {}
