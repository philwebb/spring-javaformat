import * as vscode from 'vscode'
import SpringJavaFormatter from './SpringDocumentFormattingEditProvider'

export function activate(context: vscode.ExtensionContext) {
  console.log('Activated spring-javaformat extension')
  context.subscriptions.push(
    vscode.languages.registerDocumentFormattingEditProvider(
      [
        {
          language: 'java',
          scheme: 'file',
        },
      ],
      new SpringJavaFormatter()
    )
  )
}

export function deactivate() {}
