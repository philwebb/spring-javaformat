import * as vscode from 'vscode'
import SpringDocumentFormattingEditProvider from './SpringDocumentFormattingEditProvider'

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
      new SpringDocumentFormattingEditProvider()
    )
  )
}
