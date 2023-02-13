import {
  DocumentFormattingEditProvider,
  TextDocument,
  FormattingOptions,
  CancellationToken,
  ProviderResult,
  TextEdit,
  Range,
  Disposable,
} from 'vscode'

import { ChildProcess, spawn } from 'child_process'
import { resolve } from 'path'

const JAR_PATH = resolve(__dirname, '..', 'runtime', 'spring-java-format.jar')

export default class SpringDocumentFormattingEditProvider implements DocumentFormattingEditProvider, Disposable {
  process: ChildProcess

  constructor() {
    console.log(JAR_PATH)
    const process = spawn(`java -jar ${JAR_PATH}`)
    process.stdout.on('data', (data) => {
      console.log(`stdout: ${data}`)
    })
    process.on('error', () => console.log('Error'))
    process.on('close', (code) => {
      if (code !== 0) {
        console.log(`ps process exited with code ${code}`)
      }
    })
    this.process = process
  }

  provideDocumentFormattingEdits(document: TextDocument): ProviderResult<TextEdit[]> {
    if (document.languageId === 'java') {
      return this.formatJava(document)
    }
    return []
  }

  private async formatJava(document: TextDocument): Promise<Array<TextEdit>> {
    console.log(document.fileName)
    const source = document.getText()
    const formatted = this.format(source)
    const range = new Range(document.positionAt(0), document.positionAt(source.length))
    return [TextEdit.replace(range, formatted)]
  }

  private format(source: string): string {
    return ''
  }

  dispose() {
    this.process.kill()
  }
}
