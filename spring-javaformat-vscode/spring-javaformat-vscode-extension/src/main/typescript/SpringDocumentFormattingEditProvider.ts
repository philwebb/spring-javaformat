import {
  DocumentFormattingEditProvider,
  TextDocument,
  FormattingOptions,
  CancellationToken,
  ProviderResult,
  TextEdit,
  Range,
} from 'vscode'

export default class SpringDocumentFormattingEditProvider implements DocumentFormattingEditProvider {
  provideDocumentFormattingEdits(
    document: TextDocument
  ): ProviderResult<TextEdit[]> {
    if (document.languageId === 'java') {
      const source = document.getText()
      const formatted = format(source)
      const range = new Range(document.positionAt(0), document.positionAt(source.length))
      return [TextEdit.replace(range, formatted)]    }
    return []
  }
}
function format(source: string) : string {
  return (source.startsWith("42")) ? source : "42" + source;
}
